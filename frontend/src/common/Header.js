import { NavLink } from 'react-router-dom';
import '../common/styles/commons.scss';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import ScheduleIcon from '@mui/icons-material/Schedule';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import DashboardIcon from '@mui/icons-material/Dashboard';
import FlashOnIcon from '@mui/icons-material/FlashOn';
import LunchDiningIcon from '@mui/icons-material/LunchDining';
import LogoDevIcon from '@mui/icons-material/LogoDev';
import NoteAltIcon from '@mui/icons-material/NoteAlt';
import BoltIcon from '@mui/icons-material/Bolt';
import AccountTree from '@mui/icons-material/AccountTree';


export default function Header() {
  const activeStyle = {
    fontWeight: "bold",
    backgroundColor: "gold"
  }
  const navStyle = {
    color: 'inherit',
    fontFamily: 'inherit',
    fontSize: '2rem',
    fontWeight: 'bolder',
    display: 'flex'
  }

  return (
    <header>
      <NavLink
        to="/"
        activeStyle={activeStyle}
        exact={true}
        style={navStyle}
      >
        <DashboardIcon fontSize="large" />
      </NavLink>
      <NavLink
        to="/decks"
        activeStyle={activeStyle}
        exact={true}
        style={navStyle}
      >
        <BoltIcon fontSize="large" />
      </NavLink>
      <NavLink
        to="/plans"
        activeStyle={activeStyle}
        exact={true}
        style={navStyle}
      >
        <AccountTree fontSize="large" />
      </NavLink>
      <NavLink
        to="/dev"
        activeStyle={activeStyle}
        exact={true}
        style={navStyle}
      >
        <LogoDevIcon fontSize="large" />
      </NavLink>
      <NoteAltIcon fontSize="large" />
      <NavLink
        to="/dev2"
        activeStyle={activeStyle}
        exact={true}
        style={navStyle}
      >
       i
      </NavLink>
      <ScheduleIcon fontSize="large" />
      <CalendarTodayIcon fontSize="large" />
      <LunchDiningIcon fontSize="large" />
    </header>
  )
}