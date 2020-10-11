import React, { useEffect } from 'react';
import { AppBar, Container, Typography } from '@material-ui/core';
import { makeStyles, withStyles, Theme } from '@material-ui/core/styles';

import './App.css';
import { getProductCount, getGreetings } from './APIService';
import QuantityTable from './components/QuantityTable';

const useStyles = makeStyles((theme) => ({
  root: {
    flex: 1,
    padding:10
  },
  appBar: {
    // theme.mixins.toolbar,
  },
  title: {
    flexGrow: 1,
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
      <Container>
        <QuantityTable />
      </Container>

    </div>
  );
}

export default App;
