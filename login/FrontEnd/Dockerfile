FROM node:latest
WORKDIR /

# Install dependencies based on the preferred package manager
COPY ./ ./

RUN npm install 
RUN npm run build

ENV NODE_ENV=production

EXPOSE 3000

CMD ["npm", "run", "start"]