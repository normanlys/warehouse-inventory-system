import config from "./config.json";

function getURL(path) {
    const urlString = `${config.api.scheme}://${config.api.host}${config.api.rootPath}${path}`
    return new URL(urlString)
}

export function getProductCount(code) {
    const url = getURL(`/product-count/${code}`)
    return fetch(url, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    })
    .then(res => res.json())
}

export function getGreetings() {
    const url = new URL('http://localhost:8080/api/')
    console.log(url)
    return fetch(url)
}