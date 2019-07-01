import React, { Component } from 'react';

export default class CountryVatRate extends Component {

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
        if (this.props.type === prevProps.type) { // some condition must be here otherwise infinite loop !
            return;
        }

        // TODO: Move the back-end url to some env properties
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
          .catch(err => {
            console.log(err);
            this.setState (
                { data: `Not able to fetch the data (are you offline/ is back-end running?). Reason: ${err}` }
            );
           });
    }

//     TODO: Refactor back-end to return proper JSON format and then this component
    render() {
        return (
            <div
                dangerouslySetInnerHTML={{ __html: this.state.data }}>
            </div>
        );
    }
}