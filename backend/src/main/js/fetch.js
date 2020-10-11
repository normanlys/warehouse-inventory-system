const fetch = require('node-fetch');

fetch('http://localhost:8080/api/product-entry', {
          method: 'PUT',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
          },
          body: JSON.stringify([{
              code: "s",
              location: "",
              name: "create by node",
              weight: 12
          }])
      })
      .then(res => {
        if (!res.ok) {
            throw new Error(res.statusText)
          }
      })
      .catch(err => {
          console.log(err)
      })

// fetch('http://localhost:8080/api/product-entry', {
//           method: 'POST',
//           headers: {
//               'Accept': 'application/json',
//               'Content-Type': 'application/json',
//           },
//           body: JSON.stringify({
//               code: "P9",
//               fromLocation: "ttt",
//               toLocation: "s",
//               weight: 999999
//           })
//       })
//       .then(res => {
//         if (!res.ok) {
//             res.text().then( t => {
//               throw new Error(t)
//             }).catch(err => {
//               console.log(err)
//           })
            
//           }
//       })
//       .catch(err => {
//           console.log(err)
//       })

// fetch('http://localhost:8080/api/product-count/p0y').then( res => res.json()).then( j => console.log(j))