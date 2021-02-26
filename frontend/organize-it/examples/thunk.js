const thunk = () => setTimeout(()=> console.log("lagged"), 3000);
thunk()
console.log("sync")