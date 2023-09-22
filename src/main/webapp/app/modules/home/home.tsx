import './home.scss';
import React from 'react';
import { Link } from 'react-router-dom';
import { Row, Col } from 'reactstrap';
import { useAppSelector } from 'app/config/store';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  const items = [
    { id: 1, title: 'Longer Item Title 1', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 2, title: 'Longer Item Title 2', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 3, title: 'Longer Item Title 3', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 4, title: 'Longer Item Title 4', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 5, title: 'Longer Item Title 5', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 6, title: 'Longer Item Title 6', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 7, title: 'Longer Item Title 7', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 8, title: 'Longer Item Title 8', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 9, title: 'Longer Item Title 9', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 10, title: 'Longer Item Title 10', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 11, title: 'Longer Item Title 11', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 12, title: 'Longer Item Title 12', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 13, title: 'Additional Item 1', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 14, title: 'Additional Item 2', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 15, title: 'Additional Item 3', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
    { id: 16, title: 'Additional Item 4', image: 'https://i.natgeofe.com/k/63b1a8a7-0081-493e-8b53-81d01261ab5d/red-panda-full-body_square.jpg' },
  ];

  return (

    <Row>
      {items.map(item => (
        <Col md={3} key={item.id}>
          <div className="grid-item">
            <Link to={`/item/${item.id}`} className="item-link">
              <img src={item.image} alt={item.title} className="item-image" />
              <span className="item-title">{item.title}</span>
            </Link>
          </div>
          <br/>
        </Col>
      ))}
    </Row>

  );
};

export default Home;
