import React from 'react';
import Modal from '@material-ui/core/Modal';
import Papa from 'papaparse';
import { putProductEntry } from '../APIService';
import { Button, TextField } from '@material-ui/core';

export default function AddInventoryView() {
  const [open, setOpen] = React.useState(false);
  const [location, setLocation] = React.useState("")

  const openModal = () => {
    setOpen(true);
  }

  const handleFilesUpload = event => {
    if (location == "") {
      alert("Location cannot be empty")
      return
    }

    const file = event.target.files[0]
    if (file.type != 'text/csv' || event.target.files.length > 1) {
      alert('Only accepts 1 CSV file')
      return
    }

    Papa.parse(file, {
      header: true,
      complete: (results) => {
        results.data.forEach(e => {
          if (e.weight && parseInt(e.weight) && e.name && e.code) {
            putProductEntry(e.code, location, e.name, parseInt(e.weight))
              .catch(err => alert(err))
          }
        })
      }
    })
  }

  const handleUploadButtonClick = (event) => {
    event.target.value = null
  }

  const handleLocationChange = (event) => {
    setLocation(event.target.value)
  }

  return (
    <div>
      <TextField id="outlined-basic"
        label="Location"
        variant="outlined"
        onChange={handleLocationChange}
      />

      <Button variant="contained" component="label">
        Upload CSV
      <input type="file" style={{ display: "none" }}
          onChange={handleFilesUpload}
          onClick={handleUploadButtonClick} />
      </Button>
      
      <p>CSV must contains code, weight and name</p>

    </div>
  )
}