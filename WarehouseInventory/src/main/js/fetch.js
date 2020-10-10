const fetch = require('node-fetch');

fetch('http://localhost:8080/api/product-entry', {
          method: 'PUT',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({
              code: "p2",
              location: "tsing yi",
              name: "create by put",
              weight: 2
          })
      })