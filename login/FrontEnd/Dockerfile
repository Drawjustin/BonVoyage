FROM node:latest
WORKDIR /

# Install dependencies based on the preferred package manager
COPY ./ ./

RUN npm install 
RUN npm build

EXPOSE 3000

CMD ["npm", "run", "start"]