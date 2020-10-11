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

export function putProductEntry(code, location, name, weight) {
  const url = getURL('/product-entry')
  console.log({
    code,
    location,
    name,
    weight
  })
  return fetch(url, {
    method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      code,
      location,
      name,
      weight
    })
  })
    .then(res => {
      if (!res.ok) {
        throw new Error(res.statusText)
      }
    })
}

export function moveProductEntry(code, weight, fromLocation, toLocation) {
  const url = getURL('/product-entry')
  return fetch(url, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      code,
      fromLocation,
      toLocation,
      weight
    })
  })
    .then(res => {
      if (!res.ok) {
        throw new Error(res.statusText)
      }
    })
}

export function getGreetings() {
  const url = new URL('http://localhost:8080/api/')
  console.log(url)
  return fetch(url)
}