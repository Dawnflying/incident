# 使用 Node.js 官方镜像作为构建环境
FROM node:18 AS build

# 设置工作目录
WORKDIR /app

# 将 package.json 和 package-lock.json 复制到工作目录
COPY package*.json ./

# 安装依赖
RUN npm install

# 将应用程序源代码复制到工作目录
COPY . .

# 构建应用程序
RUN npm run build

# 使用 Nginx 作为生产环境的运行环境
FROM nginx:alpine

# 复制构建后的应用程序到 Nginx 目录
COPY --from=build /app/build /usr/share/nginx/html

# 替换 Nginx 配置（可选）
# COPY nginx.conf /etc/nginx/nginx.conf

# 暴露端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
