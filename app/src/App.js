import './App.css';
import {useState} from "react";

function App() {
    const [location, setLocation] = useState('');

    const handleRestCall = () => {
        fetch(`https://sömi-weather.com/main?location=${location}`)
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <div className="App">
            <input
                type="text"
                placeholder="Enter location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
            />
            <button onClick={handleRestCall}>Get Weather</button>
        </div>
    );
}

export default App;
