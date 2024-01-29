# Use an official Node runtime as a base image
FROM node:14-alpine as build

# Set the working directory to /app
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the local source files to the working directory
COPY . .

# Build the React app
RUN npm run build

# Use an official Nginx runtime as a base image
FROM nginx:alpine

# Copy the build output from the build environment to the nginx directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx when the container starts
CMD ["nginx", "-g", "daemon off;"]
