FROM btburnett3/couchbasefakeit:enterprise-7.2.3

# Customize environment
ENV CB_DATARAM=256 \
    CB_CLUSTER_NAME=couchbase-local \
    CB_USERNAME=Administrator \
    CB_PASSWORD=password

# Copy files
COPY . /startup/