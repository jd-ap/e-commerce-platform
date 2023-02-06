# eCommerce platform

    docker build -f containerfile -t proof/e-commerce .
    docker run -p 9000:9000 proof/e-commerce --server.port=9000