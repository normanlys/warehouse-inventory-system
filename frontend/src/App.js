import React from 'react';
import { AppBar, Container } from '@material-ui/core';
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
    paddingBottom: 10,
    marginBottom: 99
  },
}));

function App() {
  const styles = useStyles

  return (
    <div className={styles.root}>
      <Container style={styles.container}>
        <AddInventoryView />
      </Container>
      <Container style={styles.container}>
        <MoveInventoryView/>
      </Container>
      <Container>
        <CheckProductWeightView />
    
      </Container>

    </div>
  );
}

export default App;
