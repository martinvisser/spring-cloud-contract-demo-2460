import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'Demo'
    request {
        method 'GET'
        url '/example'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
    }
}
