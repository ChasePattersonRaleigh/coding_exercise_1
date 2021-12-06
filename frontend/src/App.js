// import logo from './logo.svg';
// import './App.css';
// import '';
import React from "react";

class App extends React.Component {
  state = {
    people: []
  };

  async componentDidMount() {
    const response = await fetch('/api/people');
    const body = await response.json();
    console.log(body);
    this.setState({people: body});
  }

  render() { 
    return <h1>hello {this.props.name}</h1>
  }
}
// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default App;
