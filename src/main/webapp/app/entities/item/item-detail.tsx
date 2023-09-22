import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './item.reducer';

export const ItemDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const itemEntity = useAppSelector(state => state.item.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="itemDetailsHeading">Item</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{itemEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{itemEntity.name}</dd>
          <dt>
            <span id="price">Price</span>
          </dt>
          <dd>{itemEntity.price}</dd>
          <dt>
            <span id="location">Location</span>
          </dt>
          <dd>{itemEntity.location}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{itemEntity.description}</dd>
          <dt>
            <span id="image">Image</span>
          </dt>
          <dd>{itemEntity.image}</dd>
          <dt>Login</dt>
          <dd>{itemEntity.login ? itemEntity.login.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/item" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/item/${itemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ItemDetail;
