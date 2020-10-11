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

  const modalBody = (
    <p id="simple-modal-description">
      Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
    </p>
  )

  const handleFilesUpload = event => {
    if (location == "") {
      alert("Location cannot be empty")
    }

    const file = event.target.files[0]

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
        // onKeyDown={onEnter}
         />

      <Button variant="contained" component="label">Default
      <input type="file" style={{ display: "none" }} 
      onChange={handleFilesUpload} 
      onClick={handleUploadButtonClick} /> 
      </Button>
      {/* <Modal
        open={open}
      // onClose={handleClose}
      // aria-labelledby="simple-modal-title"
      // aria-describedby="simple-modal-description"
      >
        {modalBody}
      </Modal> */}
    </div>
  )
}