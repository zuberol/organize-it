
export function ListItem(props) {
    if(!props.snippet) return null;
    return (
        <div>
            <p>#{props.snippet.id}</p>
            <p>{props.snippet.title}</p>
            <p>{props.snippet.content}</p>
        </div>
    )
}