import React from 'react';
//import logo from './logo.svg';
import './App.css';
import JsonVat from './components/JsonVat';
import MenuBar from './components/MenuBar';

class App extends React.Component {

  constructor() {
    super()
    this.state = { vatType: undefined };
  }

  showVatHandler = (vatType) => {
    this.setState({ vatType });
  }

  render() {
      return (
        <div className="App">
          <MenuBar vatBtOnClickHandler={(vatType) => this.showVatHandler(vatType)}/>
          <JsonVat type={this.state.vatType} />
        </div>
      );
  }
}

export default App;
