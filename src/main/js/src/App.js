import React from 'react';
import './App.css';
import CountryVatRate from './components/CountryVatRate';
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
          <CountryVatRate type={this.state.vatType} />
        </div>
      );
  }
}

export default App;
