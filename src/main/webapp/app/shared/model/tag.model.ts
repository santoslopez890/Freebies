import { IItem } from 'app/shared/model/item.model';

export interface ITag {
  id?: number;
  title?: string | null;
  items?: IItem[] | null;
}

export const defaultValue: Readonly<ITag> = {};
