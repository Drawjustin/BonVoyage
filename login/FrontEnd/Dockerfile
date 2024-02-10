# Use an official Node runtime as a base image
FROM node:latest as build

# Set the working directory to /app
WORKDIR /

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the local source files to the working directory
COPY . .
RUN rm -rf /etc/nginx/conf.d
# Build the React app
RUN npm run build

# Use an official Nginx runtime as a base image
FROM nginx:latest

# Copy the custom nginx.conf file to the nginx configuration directory

COPY default.conf /etc/nginx/conf.d/default.conf
# Copy the build output from the build environment to the nginx directory
COPY --from=build /.next /usr/share/nginx/html

# Expose port 80
EXPOSE 3000

# Start Nginx when the container starts
CMD ["npm", "run", "start"]