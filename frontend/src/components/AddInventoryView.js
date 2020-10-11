import React from 'react';
import Papa from 'papaparse';
import { putProductEntries } from '../APIService';
import { Button, TextField, Grid } from '@material-ui/core';

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
        const entries = results.data
          .filter(e => {
            return e.weight && parseInt(e.weight) && e.name && e.code
          })
          .map(e => {
            return {
              weight: parseInt(e.weight),
              name: e.name,
              code: e.code,
              location: location
            }
          })
        
        if (entries.length == 0) {
          alert('Incorrect format or missing data')
          return
        }

        putProductEntries(entries)
          .then(() => {
            alert('Successfully upload CSV')
          })
          .catch(err => alert(err))
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
    <Grid container spacing={2} alignItems='center'>
      <Grid item>
        <TextField id="outlined-basic"
          label="Location"
          variant="outlined"
          onChange={handleLocationChange}
        />
      </Grid>

      <Grid item>
        <Button variant="contained" component="label">
          Upload CSV
          <input type="file" style={{ display: "none" }}
            onChange={handleFilesUpload}
            onClick={handleUploadButtonClick} />
        </Button>
      </Grid>

      <Grid item>
        <p>*CSV must contains code, weight and name</p>
      </Grid>
    </Grid>
  )
}