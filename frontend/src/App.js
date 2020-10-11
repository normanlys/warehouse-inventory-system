import React from 'react';
import { AppBar, Container } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

import './App.css';
import CheckProductWeightView from './components/CheckProductWeightView';
import AddInventoryView from './components/AddInventoryView';
import MoveInventoryView from './components/MoveInventoryView';

const useStyles = makeStyles((theme) => ({
  root: {
    flex: 1,
    padding:10,
    alignItems: 'center',
    justifyContent: 'space-between'
  },
  appBar: {
    // theme.mixins.toolbar,
  },
  title: {
    flexGrow: 1,
  },
  container: {
    padding: 10
  },
}));

function App() {
  const styles = useStyles

  return (
    <div className={styles.root}>
      <Container style={styles.container}>
        <AddInventoryView />
        <MoveInventoryView/>
      </Container>
      <Container>
        <CheckProductWeightView />
    
      </Container>

    </div>
  );
}

export default App;
