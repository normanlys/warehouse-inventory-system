import config from "./config.json";

function getURL(path) {
    return new URL(`${config.api.scheme}//${config.api.host}${config.api.rootPath}${path}`)
}

export function getProductCount(code) {
    const url = new URL('http://localhost:8080/api/product-count/' + code)
    console.log(url)
    return fetch(url, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    })
}

export function getGreetings() {
    const url = new URL('http://localhost:8080/api/')
    console.log(url)
    return fetch(url)
}