// src/services/incidentService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/incidents'; // 替换为你的实际 API 地址

export const getIncidents = () => {
    return axios.get(API_URL);
};

export const getIncidentById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const createIncident = (incident) => {
    return axios.post(API_URL, incident);
};

export const updateIncident = (id, incident) => {
    return axios.put(`${API_URL}/${id}`, incident);
};

export const deleteIncident = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
