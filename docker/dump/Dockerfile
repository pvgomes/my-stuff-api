FROM mongo:latest
COPY stuffs.json /stuffs.json
COPY owners.json /owners.json
COPY places.json /places.json
CMD mongoimport -h mongodb -d mystuff -c owners --file /owners.json --jsonArray && mongoimport -h mongodb -d mystuff -c places --file /places.json --jsonArray && mongoimport -h mongodb -d mystuff -c stuffs --file /stuffs.json --jsonArray