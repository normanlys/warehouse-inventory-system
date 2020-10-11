import React, { useEffect, useState, useLayoutEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { Button, Container, TextField } from '@material-ui/core';

import { getProductCount } from '../APIService';

const useStyles = makeStyles({
  table: {
    minWidth: 200,
  },
});

export default function QuantityTable() {

  const classes = useStyles();
  const [code, setCode] = React.useState("")
  const [rows, setRows] = React.useState([]);

  const onCheckButtonClick = () => {
    if (code == "") {
      alert("Product Code cannot be empty")
      return
    }
    getProductCount(code)
      .then(json => {
        if (json.length == 0) {
          setRows([{location: "No Products Found"}])
        } else {
          setRows(json)
        }
      })
      .catch(err => alert(err))
  }

  const handleProductCodeChange = (e) => {
    setCode(e.target.value)
  }

  const onEnter = (e) => {
    if (e.keyCode == 13) {
      onCheckButtonClick()
    }
  }

  return (
    <Container>
      <TextField id="outlined-basic"
        label="Product Code"
        variant="outlined"
        onChange={handleProductCodeChange}
        onKeyDown={onEnter} />

      <Button onClick={onCheckButtonClick}>
        Check Product Weight
      </Button>

      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Location</TableCell>
              <TableCell align="right">Weight</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow key={row.location}>
                <TableCell component="th" scope="row">
                  {row.location}
                </TableCell>
                <TableCell align="right">{row.weight}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Container>
  );
}
