# Use an official Node.js image as base
FROM node:latest

# Install necessary system dependencies
RUN apt-get update && apt-get install -y nginx

# Set up working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install npm dependencies
RUN npm install

# Copy the application code into the container
COPY . .

# Build the application using npx (assuming it's a Next.js project)
RUN npx next build

# Copy the built files to the Nginx HTML directory
RUN cp -r .next/* /usr/share/nginx/html

# Expose port 3000
EXPOSE 3000

# Start Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]
