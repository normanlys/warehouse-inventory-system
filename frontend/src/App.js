import React from 'react';
import { Container, Divider } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

import CheckProductWeightView from './components/CheckProductWeightView';
import AddInventoryView from './components/AddInventoryView';
import MoveInventoryView from './components/MoveInventoryView';

const useStyles = makeStyles((theme) => ({
  root: {},
  container: {
    margin: 10,
  },
}));

function App() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Container className={classes.container}>
        <h2>Add Inventory</h2>
        <AddInventoryView />
      </Container>

      <Divider variant="middle" />
      <Container className={classes.container}>
        <h2>Move Inventory</h2>
        <MoveInventoryView />
      </Container>

      <Divider variant="middle" />
      <Container className={classes.container}>
        <h2>Check Inventory</h2>
        <CheckProductWeightView />
      </Container>

    </div>
  );
}

export default App;
