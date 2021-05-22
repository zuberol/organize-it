import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Switch from '@material-ui/core/Switch';
import Paper from '@material-ui/core/Paper';
import Fade from '@material-ui/core/Fade';
import FormControlLabel from '@material-ui/core/FormControlLabel';



export default function SimpleFade() {
  const [checked, setChecked] = React.useState(false);

  const handleChange = () => {
  setChecked((prev) => !prev);
  };

  return (
  <div>
    <FormControlLabel
    control={<Switch checked={checked} onChange={handleChange} />}
    label="Show"
    />
    <div>
    <Fade in={checked}>
      <h2>hello</h2>
    </Fade>
    </div>
  </div>
  );
}
