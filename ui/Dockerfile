FROM node:13.12.0-alpine
WORKDIR /usr/src/app/ui
COPY package*.json ./
RUN npm install
EXPOSE 3000
EXPOSE 3001
CMD ["npm", "start"]