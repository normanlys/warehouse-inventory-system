import React from 'react';
import { Container, Divider } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

import CheckProductWeightView from './components/CheckProductWeightView';
import AddInventoryView from './components/AddInventoryView';
import MoveInventoryView from './components/MoveInventoryView';

const useStyles = makeStyles((theme) => ({
  root: {
    // flex: 1,
    // alignItems: 'center',
    // justifyContent: 'space-between'
  },
  container: {
    margin: 10,
  },
}));

function App() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <Container className={styles.container}>
        <h2>Add Inventory</h2>
        <AddInventoryView />
      </Container>

      <Divider variant="middle" />
      <Container className={styles.container}>
        <h2>Move Inventory</h2>
        <MoveInventoryView />
      </Container>

      <Divider variant="middle" />
      <Container className={styles.container}>
        <h2>Check Inventory</h2>
        <CheckProductWeightView />
      </Container>

    </div>
  );
}

export default App;
