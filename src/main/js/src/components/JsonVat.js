import React, { Component } from 'react';

export default class JsonVat extends Component {

    propTypes: {
        type: React.PropTypes.string.Required
    }

    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            data: []
        };
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
//        console.log('VAT type choice (was: ' + prevProps.type + ', will be: ' + this.props.type + ')');

        if (this.props.type === prevProps.type) { // must be here otherwise infinite loop !
            return;
        }

        fetch(`http://localhost:8080/countries/vat/${this.props.type}?count=4`)
          .then(response =>
            {
                if (!response.ok) {
                    throw Error(response.statusText);
                }
                return response.text()
            })
          .then(output => this.setState(
            { data: output }
           ))
          .catch(err => console.log(err));
    }

    render() {
        return (
            <div
                dangerouslySetInnerHTML={{ __html: this.state.data }}>
            </div>
        );
    }
}