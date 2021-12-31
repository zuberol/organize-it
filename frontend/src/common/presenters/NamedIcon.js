import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export function NamedIcon(props) {
    if(!props.iconDef && props.caption) return null;
    const { iconDef, caption } = props;
    return (
        <div 
            onClick={props.onClick}
            style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            {<FontAwesomeIcon size={'lg'}  icon={iconDef}/>}
            <span>{caption}</span>
        </div>

    )
}