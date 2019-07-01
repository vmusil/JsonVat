import React, { Component } from 'react';

export default class MenuBar extends Component {

    propTypes: {
        // the MenuBar requires a clickHandler
        vatBtOnClickHandler: React.PropTypes.func.Required
    }

    render() {
        return (
            <>
                <button
                    type="button"
                    className="btn btn-primary"
                    onClick={this.props.vatBtOnClickHandler.bind(this, 'lowest')}
                 >
                    Countries with the lowest VAT rate
                </button>

                <button
                    type="button"
                    className="btn btn-success"
                    onClick={this.props.vatBtOnClickHandler.bind(this, 'highest')}
                >
                    Countries with the highest VAT rate
                </button>
            </>
        );
    }
}