import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export function NamedIcon(props) {
    if(!props.iconDef && props.caption) return null;
    const { iconDef, caption } = props;
    return (
        <div className="fire-streak">
            {<FontAwesomeIcon icon={iconDef}/>}
            <span>{caption}</span>
        </div>

    )
}