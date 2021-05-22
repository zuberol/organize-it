import { makeStyles } from '@material-ui/core/styles';



const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: 'blue'
  },
  paper: {
    backgroundColor: 'tan',
    boxShadow: theme.shadows[5],
    padding: "2rem"
  },
}));


const classes = useStyles();
