document.addEventListener('selectionchange', event => {
    console.log(document.getSelection().anchorNode);
    console.log(document.getSelection().focusNode);
    console.log(document.getSelection().focusOffset);
});