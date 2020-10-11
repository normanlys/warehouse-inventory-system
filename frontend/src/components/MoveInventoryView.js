import React from 'react';
import Button from '@material-ui/core/Button';
import { Grid, TextField } from '@material-ui/core';
import { moveProductEntry } from '../APIService';

export default function MoveInventoryView() {
  const [code, setCode] = React.useState();
  const [weight, setWeight] = React.useState();
  const [fromLocation, setFromLocation] = React.useState();
  const [toLocation, setToLocation] = React.useState();

  const handleCodeChange = (event) => {
    setCode(event.target.value)
  }

  const handleWeightChange = (event) => {
    setWeight(event.target.value)
  }

  const handleFromLocationChange = (event) => {
    setFromLocation(event.target.value)
  }

  const handleToLocationChange = (event) => {
    setToLocation(event.target.value)
  }

  const handleMoveInventoryClick = (event) => {
    if (!code || !weight || !fromLocation || !toLocation) {
      alert('Fields cannot be empty')
      return
    }

    if (!parseInt(weight)) {
      alert('Weight must be integer')
      return
    }

    moveProductEntry(code, parseInt(weight), fromLocation, toLocation)
      .then(() => {
        alert('Successfully moved inventory')
      })
      .catch(err => {
        alert('Failed to move inventory')
      })
  }

  return (
    <Grid container spacing={2} alignItems='center'>
      <Grid item>
        <TextField id="outlined-basic"
          label="Product Code"
          variant="outlined"
          onChange={handleCodeChange} />
      </Grid>
      <Grid item>
        <TextField id="outlined-basic"
          label="Weight"
          variant="outlined"
          onChange={handleWeightChange} />
      </Grid>
      <Grid item>
        <TextField id="outlined-basic"
          label="From Location"
          variant="outlined"
          onChange={handleFromLocationChange} />
      </Grid>
      <Grid item>
        <TextField id="outlined-basic"
          label="To Location"
          variant="outlined" 
          onChange={handleToLocationChange} />
      </Grid>
      <Grid item>
        <Button variant="contained" onClick={handleMoveInventoryClick}>Move inventory</Button>
      </Grid>
    </Grid>
  )
}