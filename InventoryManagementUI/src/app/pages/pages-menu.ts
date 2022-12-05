import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
 
  {
    title: 'Dashboard',
    icon: 'home-outline',
    link: '/pages/dashboard',
    home: true,
  },
  {
    icon: 'layout-outline',
    title: 'Server',
    link: '/pages/server/servers',
  },
  {
    icon: 'layout-outline',
    title: 'Device',
    link: '/pages/device/devices',
  },
  {
    icon: 'layout-outline',
    title: 'Client',
    link: '/pages/client/clients',
  },
  {
    icon: 'layout-outline',
    title: 'Browser',
    link: '/pages/browser/browses',
  },
  {
    icon: 'layout-outline',
    title: 'Others',
    link: '/pages/others/otherses',
  },
  {
    icon: 'layout-outline',
    title: 'Audit',
    link: '/pages/audit',
  },
  {
    icon: 'layout-outline',
    title: 'Metaverse',
    link: '/pages/metavers',
  },
  {
    icon: 'layout-outline',
    title: 'Tree-View',
    link: '/pages/treeView',
  }
];
