import { watchGetUsers, watchCancelUserUpdate, watchDeleteUser, watchEditUser, watchPostUser, watchPutUser } from './users';

export default function* () {
  yield [
    watchGetUsers(),
    watchCancelUserUpdate(),
    watchDeleteUser(),
    watchEditUser(),
    watchPostUser(),
    watchPutUser()
  ]
  console.log('root saga')
}