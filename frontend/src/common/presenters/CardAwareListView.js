export default function useCardAwareListView(presentable) {
    let presenterJsx;
    const { presenter } = presentable;
    if(typeof presenter === 'function') presenterJsx = presenter.call(this);
    else presenterJsx = <div>Not presentable</div>;
    return presenterJsx;
}