import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description("should return books list")
    request {
        url("/book/v1.1")
        method GET()
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body '''\

                        [{
                            "id": 28,
                            "title": "mr",
                            "author": "a1",
                            "category": "Love2",
                            "description": "its fine"
                        }]
        '''

    }
}