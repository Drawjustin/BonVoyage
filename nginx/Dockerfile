# Dockerfile

# Use an official NGINX image as a base image
FROM nginx:latest

# Expose the port where NGINX will listen

COPY ./default.conf /etc/nginx/conf.d/default.conf

COPY cert.pem /etc/nginx/certs/cert.pem
COPY key.pem /etc/nginx/certs/key.pem


EXPOSE 443
# Start NGINX when the container starts
CMD ["nginx", "-g", "daemon off;"]

