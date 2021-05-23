import { createStore, applyMiddleware } from 'redux';
import rootReducer from './reducers';
import reduxSaga from 'redux-saga';
import rootSaga from './sagas';

export const getStore = () => {
  const initialState = {};
  const reduxSagaMiddleware = reduxSaga();
  const store = createStore(rootReducer, initialState, applyMiddleware(reduxSagaMiddleware));
  reduxSagaMiddleware.run(rootSaga)
  return store;
}