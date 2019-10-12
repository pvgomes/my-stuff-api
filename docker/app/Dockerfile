FROM clojure
COPY . /usr/src/app
WORKDIR /usr/src/app
ENV MONGODB=mongodb://mongodb:27017/mystuff
RUN lein deps
#CMD ["lein", "run"]
EXPOSE 42487