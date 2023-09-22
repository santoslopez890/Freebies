import { IUser } from 'app/shared/model/user.model';
import { ITag } from 'app/shared/model/tag.model';

export interface IItem {
  id?: number;
  name?: string | null;
  price?: number | null;
  location?: string | null;
  description?: string | null;
  image?: string | null;
  login?: IUser | null;
  tags?: ITag[] | null;
}

export const defaultValue: Readonly<IItem> = {};
