import React, { useState, useEffect } from 'react';
import { getIncidents, getIncidentById, createIncident, updateIncident, deleteIncident } from './services/incidentService';
import './App.css'; // 导入 CSS 文件

function App() {
  const [incidents, setIncidents] = useState([]);
  const [currentIncident, setCurrentIncident] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    status: ''
  });

  useEffect(() => {
    fetchIncidents();
  }, []);

  const fetchIncidents = async () => {
    try {
      const response = await getIncidents();
      setIncidents(response.data);
    } catch (error) {
      console.error('Error fetching incidents:', error);
    }
  };

  const handleCreate = async () => {
    try {
      await createIncident(formData);
      fetchIncidents();
      setFormData({ title: '', description: '', status: '' });
    } catch (error) {
      console.error('Error creating incident:', error);
      showError('Error creating incident. Please try again later.');
    }
  };

  // 显示错误提示
  const showError = (message) => {
    alert(message);
  };

  const handleUpdate = async (id) => {
    try {
      await updateIncident(id, formData);
      fetchIncidents();
      setFormData({ title: '', description: '', status: '' });
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating incident:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteIncident(id);
      fetchIncidents();
    } catch (error) {
      console.error('Error deleting incident:', error);
    }
  };

  const handleEdit = (incident) => {
    setCurrentIncident(incident);
    setFormData({
      title: incident.title,
      description: incident.description,
      status: incident.status
    });
    setIsEditing(true);
  };

  return (
      <div className="container">
        <h1 className="header">Incident Management</h1>
        <div className="form-container">
          <h2>{isEditing ? 'Edit Incident' : 'Create Incident'}</h2>
          <input
              type="text"
              placeholder="Title"
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
          />
          <input
              type="text"
              placeholder="Description"
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
          />
          <input
              type="text"
              placeholder="Status"
              value={formData.status}
              onChange={(e) => setFormData({ ...formData, status: e.target.value })}
          />
          {isEditing ? (
              <button onClick={() => handleUpdate(currentIncident.id)}>Update</button>
          ) : (
              <button onClick={handleCreate}>Create</button>
          )}
        </div>
        <div>
          <h2>Incidents List</h2>
          <ul className="incident-list">
            {incidents.map((incident) => (
                <li key={incident.id} className="incident-item">
                  <strong>{incident.title}</strong>
                  <p>{incident.description}</p>
                  <p>Status: {incident.status}</p>
                  <button className="edit" onClick={() => handleEdit(incident)}>Edit</button>
                  <button className="delete" onClick={() => handleDelete(incident.id)}>Delete</button>
                </li>
            ))}
          </ul>
        </div>
      </div>
  );
}

export default App;
