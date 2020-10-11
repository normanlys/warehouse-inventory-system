import React, { useEffect, useLayoutEffect } from 'react';
import { AppBar, Container, Typography } from '@material-ui/core';
import { makeStyles, withStyles, Theme } from '@material-ui/core/styles';

import './App.css';
import CheckProductWeightView from './components/CheckProductWeightView';
import AddInventoryView from './components/AddInventoryView';

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
      {/* <Container>
      <AppBar>
        <Typography variant="h6">
          News
        </Typography>
      </AppBar>
      </Container> */}
      <Container style={styles.container}>
        <AddInventoryView />
        {/* <MoveInventoryButton/> */}
      </Container>
      <Container>
        <CheckProductWeightView />

      </Container>

    </div>
  );
}

export default App;
