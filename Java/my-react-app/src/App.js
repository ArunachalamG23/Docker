import React from "react";
import MessageForm from "./components/MessageForm"; // Import MessageForm

const App = () => {
    return (
        <div>
            <MessageForm /> {/* Only render MessageForm component */}
        </div>
    );
};

export default App;

