import axios from 'axios';

export async function getHelloMessage() {
  return (await axios.get('/api/csvdupl')).data;
}
